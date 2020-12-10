package technocite.tn.telecite.repositories;

import java.util.List;
import java.util.Optional;

import technocite.tn.telecite.dto.ReunionDto;


public interface ReunionService {
	   
	    public ReunionDto addReunion(ReunionDto reunionDto);
	    public List<ReunionDto> getAllReunions();
	    public ReunionDto updateReunion(Long idReunion, ReunionDto reunion);
	    public String deleteReunion(Long idReunion);
		public Optional<ReunionDto> findById(Long idReunion);

}
