package technocite.tn.telecite.dto;

import java.util.List;
import java.util.Optional;


public interface ReunionService {
	   
	    public ReunionDto addReunion(ReunionDto reunionDto);
	    public List<ReunionDto> getAllReunions();
	    public ReunionDto updateReunion(Long idReunion, ReunionDto reunion);
	    public String deleteReunion(Long idReunion);
		public Optional<ReunionDto> findById(Long idReunion);

}
