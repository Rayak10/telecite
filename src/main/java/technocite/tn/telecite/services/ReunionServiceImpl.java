package technocite.tn.telecite.services;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import technocite.tn.telecite.dto.ReunionDto;
import technocite.tn.telecite.dto.TimeDTO;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.enums.ReunionType;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IMail;
import technocite.tn.telecite.repositories.IReunion;
import technocite.tn.telecite.repositories.ReunionService;
@Service

public class ReunionServiceImpl implements ReunionService {

	
	@Resource
    private IReunion reunionRepository;
 
    @Resource
    private IEmploye employeRepository;
    @Resource
    private IMail emailrepository;
    
	@Override
	public ReunionDto addReunion(ReunionDto reunionDto) {
		int i;
		String Newligne=System.getProperty("line.separator");
		SimpleDateFormat formater = null;
	    formater = new SimpleDateFormat("dd-MM-yy");

		Reunion reunion = new Reunion();
	        mapDtoToEntity(reunionDto, reunion);
	        Reunion savedReunion = reunionRepository.save(reunion);
	        System.out.println(reunion+"ùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùù");
	       for(i=0;i<=reunion.getEmployes().size();i++) {
	emailrepository.sendEmail(reunion.getEmployes().get(i).getEmail()," "+
							reunion.getType(),"Bonjour Nous avons l’honneur de vous convier à une réunion d’information le "+
							formater.format(reunion.getDateDebut())+" à "+(reunion.getHeurDeb().getHour()-1)+"h"+
							(reunion.getHeurDeb().getMinute())+"mnt."+Newligne+" Cette rencontre sera l’occasion d’aborder "+
                             reunion.getNomReunion()+"."+" Espérant vous compter parmi les membres présents, nous vous prions d’agréer,"+
			                  reunion.getEmployes().get(i).getPrenomEmploye()+" "+reunion.getEmployes().get(i).getNomEmploye()+"."+Newligne+
			                  " l’expression de nos sentiments les meilleurs."+Newligne+"lien de reunion: http://localhost:5000/"+Newligne+"clé de session :Session"+reunion.getIdReunion());	
}
	        return mapEntityToDto(savedReunion);
	}
	
	


	@Override
	public List<ReunionDto> getAllReunions() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReunionDto updateReunion(Long idReunion, ReunionDto reunionDto) {
	        
		        Reunion r = reunionRepository.getOne(idReunion);
		        r.getEmployes().clear();
		        mapDtoToEntity(reunionDto, r);
		        Reunion reunion = reunionRepository.save(r);
		        return mapEntityToDto(reunion);
		    }
		 // que?

	@Override
	public String deleteReunion(Long idReunion) {
		// TODO Auto-generated method stub
		return null;
	}

	
	  private void mapDtoToEntity(ReunionDto reunionDto, Reunion reunion) {
	        reunion.setNomReunion(reunionDto.getNomReunion());
	        reunion.setDateDebut(reunionDto.getDateDebut());
	        reunion.setDateFin(reunionDto.getDateFin());
	        reunion.setDescriptionReunion(reunionDto.getDescriptionReunion());
	       reunion.setHeurDeb(LocalTime.of(reunionDto.getHeureDeb().getHour()+1,reunionDto.getHeureDeb().getMinute(),00));
	       reunion.setHeurFin(LocalTime.of(reunionDto.getHeureFin().getHour()+1,reunionDto.getHeureFin().getMinute(),00));
	        reunion.setEquipe(reunionDto.getEquipe());
	        reunion.setType(reunionDto.getType());

	        
	        if (null == reunion.getEmployes()) {
	            reunion.setEmployes(new ArrayList<>());
	        }
	        if (reunionDto.getType()==ReunionType.Reunion_Scrum) {
	        	 reunion.setEmployes(employeRepository.findByEquipe(reunionDto.getEquipe()));
	        	 
	        	 System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddd");
	        }
	        else {
	        	  reunionDto.getEmployes().stream().forEach(id -> {
	  	            Employe employe = employeRepository.findEmployeByIdEmploye(id);
	  	            if (null == employe) {
	  	                employe = new Employe() ;
	  	                employe.setReunions(new HashSet<>());
	  	            }
	  	            employe.setIdEmploye(id);
	  	            reunion.addEmploye(employe);
		        	 System.out.println("ttttttttttttttttttttttttttttttttttttttttttttttt");

	  	        });
	        }
	        
	        
	        
	      
	    }
	 
	    private ReunionDto mapEntityToDto(Reunion reunion) {
	    	ReunionDto responseDto = new ReunionDto();
	        responseDto.setIdReunion(reunion.getIdReunion());
	        responseDto.setNomReunion(reunion.getNomReunion());
	        responseDto.setDateFin(reunion.getDateFin());
	        responseDto.setDescriptionReunion(reunion.getDescriptionReunion());
	        responseDto.setEquipe(reunion.getEquipe());
	        responseDto.setHeureDeb(new TimeDTO(reunion.getHeurDeb().getHour()-1, reunion.getHeurDeb().getMinute(), 00));
	        responseDto.setHeureFin(new TimeDTO(reunion.getHeurFin().getHour()-1, reunion.getHeurFin().getMinute(), 00));
       	 System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");

	        responseDto.setDateDebut(reunion.getDateDebut());
	        responseDto.setType(reunion.getType());
        responseDto.setEmployes(reunion.getEmployes().stream().map(Employe::getIdEmploye).collect(Collectors.toSet()));
	        return responseDto;
	    }




		@Override
		public Optional<ReunionDto> findById(Long idReunion) {
			Optional<Reunion> reunion = reunionRepository.findById(idReunion);
			ReunionDto responseDto = null;
			if(reunion.isPresent())
				responseDto = mapEntityToDto(reunion.get());

			return Optional.of(responseDto);
		}
}
