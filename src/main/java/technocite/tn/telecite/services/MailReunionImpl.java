package technocite.tn.telecite.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LocalTimeType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import technocite.tn.telecite.dto.MailReunionDTO;

@Repository
public class MailReunionImpl {
	@Autowired
	private EntityManager em;

	List<MailReunionDTO> getAdministrativeReunionMailInfo() {
		String sql = "select r.`id_reunion` as idReunion,r.nom_reunion as nomReunion, emp.email as mail , concat(emp.nom_employe, ' ', emp.prenom_employe) as nameEmploye, r.`heur_deb` as heureDeb, r.`date_debut`as startDate, 'REUNION_ADMINISTRATIF' as typeReunion From reunion r join reunion_employe r_emp on r_emp.reunion_id = r.`id_reunion` join employe emp on r_emp.employe_id = emp.id_employe where DATE_FORMAT(r.date_debut,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') AND r.type ='Reunion_Administratif'";

		try {
		return em.createNativeQuery(sql).unwrap(SQLQuery.class).addScalar("idReunion", new IntegerType())
				.addScalar("mail", new StringType()).addScalar("nameEmploye", new StringType())
				.addScalar("heureDeb", new LocalTimeType()).addScalar("startDate", new DateType())
				.addScalar("typeReunion", new StringType())
				.addScalar("nomReunion", new StringType())
				.setResultTransformer(Transformers.aliasToBean(MailReunionDTO.class)).getResultList();
		}catch(Exception e) {e.printStackTrace();}
		return null;

	}
	
	List<MailReunionDTO>  getSrumReunionMailInfo() {
		String sql = "select r.`id_reunion` as idReunion,r.nom_reunion as nomReunion, emp.email as mail , concat(emp.nom_employe, ' ', emp.prenom_employe) as nameEmploye, r.`heur_deb` as heureDeb, r.`date_debut`as startDate, 'REUNION_SCRUM' as typeReunion From reunion r  join employe emp on r.fk_eq_rsc_id = emp.fk_dep_emp_id where DATE_FORMAT(r.date_debut,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') AND r.type ='Reunion_Scrum'";

		try {
		return em.createNativeQuery(sql).unwrap(SQLQuery.class).addScalar("idReunion", new IntegerType())
				.addScalar("mail", new StringType()).addScalar("nameEmploye", new StringType())
				.addScalar("heureDeb", new LocalTimeType()).addScalar("startDate", new DateType())
				.addScalar("typeReunion", new StringType())
				.addScalar("nomReunion", new StringType())
				.setResultTransformer(Transformers.aliasToBean(MailReunionDTO.class)).getResultList();
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}
}
