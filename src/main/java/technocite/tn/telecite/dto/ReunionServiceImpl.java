package technocite.tn.telecite.dto;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.enums.ReunionType;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IReunion;
@Service

public class ReunionServiceImpl implements ReunionService {

	
	@Resource
    private IReunion reunionRepository;
 
    @Resource
    private IEmploye employeRepository;
 
    
	@Override
	public ReunionDto addReunion(ReunionDto reunionDto) {
		 Reunion reunion = new Reunion();
	        mapDtoToEntity(reunionDto, reunion);
	        Reunion savedReunion = reunionRepository.save(reunion);
	        return mapEntityToDto(savedReunion);
	}

	@Override
	public List<ReunionDto> getAllReunions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReunionDto updateReunion(Long idReunion, ReunionDto reunion) {
		// TODO Auto-generated method stub
		return null;
	}

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
	       // reunion.setHeurDeb(LocalTime.of(reunion.getHeureDeb().getHour()+1,reunion.getHeureDeb().getMinute(),00));
	       // reunion.setHeurFin(LocalTime.of(reunion.getHeureFin().getHour()+1,reunion.getHeureFin().getMinute(),00));
	        reunion.setEquipe(reunionDto.getEquipe());
	        reunion.setType(reunionDto.getType());
	        
	        if (null == reunion.getEmployes()) {
	            reunion.setEmployes(new HashSet<>());
	        }
	        reunionDto.getEmployes().stream().forEach(id -> {
	            Employe employe = employeRepository.findEmployeByIdEmploye(id);
	            if (null == employe) {
	                employe = new Employe() ;
	                employe.setReunions(new HashSet<>());
	            }
	            employe.setIdEmploye(id);
	            reunion.addEmploye(employe);
	        });
	    }
	 
	    private ReunionDto mapEntityToDto(Reunion reunion) {
	    	ReunionDto responseDto = new ReunionDto();
	        responseDto.setIdReunion(reunion.getIdReunion());
	        responseDto.setNomReunion(reunion.getNomReunion());
	        responseDto.setDateFin(reunion.getDateFin());
	        responseDto.setDescriptionReunion(reunion.getDescriptionReunion());
	        responseDto.setEquipe(reunion.getEquipe());
	       // responseDto.setHeurDeb(LocalTime.of(responseDto.getHeureDeb().getHour()+1,responseDto.getHeureDeb().getMinute(),00));
	       // responseDto.setHeurFin(LocalTime.of(responseDto.getHeureFin().getHour()+1,responseDto.getHeureFin().getMinute(),00));
	        responseDto.setHeureDeb(reunion.getHeureDeb());
	        responseDto.setHeureFin(reunion.getHeureFin());
	        responseDto.setDateDebut(reunion.getDateDebut());
	        responseDto.setType(reunion.getType());
        responseDto.setEmployes(reunion.getEmployes().stream().map(Employe::getIdEmploye).collect(Collectors.toSet()));
	        return responseDto;
	    }
}
