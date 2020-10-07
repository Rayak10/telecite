package technocite.tn.telecite.dto;

import java.util.List;

import technocite.tn.telecite.entities.Reunion;

public interface ReunionService {
	   
	    public ReunionDto addReunion(ReunionDto reunionDto);
	    public List<ReunionDto> getAllReunions();
	    public ReunionDto updateReunion(Long idReunion, ReunionDto reunion);
	    public String deleteReunion(Long idReunion);

}
