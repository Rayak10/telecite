package technocite.tn.telecite.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technocite.tn.telecite.entities.Postit;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IPostit;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/postits")
public class PostitController {
	@Autowired
	private IPostit postitRepository;
	@Autowired
	private IEmploye employeRepository;
	@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(postitRepository.findAll());
	}
@GetMapping("/{idPostit}")
	
	public ResponseEntity findPostitById(@PathVariable(name="idPostit") Long idPostit) { 
		
		  if (idPostit == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Postit with null ID");
	        }
	        Optional<Postit> postit = postitRepository.findById(idPostit);
	        if (postit == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(postit);
	}



@PostMapping("/")
public ResponseEntity createPostit(@RequestBody Postit postit) {
if (postit == null) {
    return ResponseEntity.badRequest().body("Cannot create postit with empty fields");
}
Postit createPostit = postitRepository.save(postit);
return ResponseEntity.ok(createPostit);
}


@PutMapping("/update/{idPostit}")
public ResponseEntity<Postit> updatePostit(@PathVariable(value = "idPostit") Long idPostit, @RequestBody 
		Postit postitDetails) throws ResourceNotFoundException {
	    Postit postit = postitRepository.findById(idPostit)
			.orElseThrow(() -> new ResourceNotFoundException("buraeu not found for this id :: " + idPostit));
	
	postit.setDescriptionPostit(postitDetails.getDescriptionPostit());
	
	final Postit updatedPostit = postitRepository.save(postit);
	return ResponseEntity.ok(updatedPostit);
}

@DeleteMapping("/{idPostit}")
public ResponseEntity deletePostit(@PathVariable(name = "idPostit") Long idPostit) {
    if (idPostit == null) {
        return ResponseEntity.badRequest().body("Cannot remove Postit with null ID");
    }
    Postit postit = postitRepository.getOne(idPostit);
    if (postit == null) {
        return  ResponseEntity.notFound().build();
    }
    postitRepository.delete(postit);
    return ResponseEntity.ok(postitRepository.findAll());
}
}
