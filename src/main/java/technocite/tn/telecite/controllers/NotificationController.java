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
import technocite.tn.telecite.entities.Notification;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.repositories.INotification;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/notifications")
public class NotificationController   {
	@Autowired
	private INotification notificationRepository;
	
	
@GetMapping("/")
	public ResponseEntity findAll() {
		
		return  ResponseEntity.ok(notificationRepository.findAll());
	}
@GetMapping("/{idNotification}")
	
	public ResponseEntity findNotificationById(@PathVariable(name="idNotification") Long idNotification) { 
		
		  if (idNotification == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve notification with null ID");
	        }
	        Optional<Notification> notification = notificationRepository.findById(idNotification);
	        if (notification == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(notification);
	}

@PostMapping("/")
public ResponseEntity createNotification(@RequestBody Notification notification) {
if (notification == null) {
    return ResponseEntity.badRequest().body("Cannot create notification with empty fields");
}
Notification createNotification = notificationRepository.save(notification);
return ResponseEntity.ok(createNotification);
}


@PutMapping("/update/{idNotification}")
public ResponseEntity<Notification> updateNotification(@PathVariable(value = "idNotification") Long idNotification, @RequestBody 
		Notification notificationDetails) throws ResourceNotFoundException {
	Notification notification = notificationRepository.findById(idNotification)
			.orElseThrow(() -> new ResourceNotFoundException("buraeu not found for this id :: " + idNotification));
	
	notification.setNomNotification(notificationDetails.getNomNotification());
	notification.setLibelleNotification(notificationDetails.getLibelleNotification());

	final Notification updatedNotification= notificationRepository.save(notification);
	return ResponseEntity.ok(updatedNotification);
}

@DeleteMapping("/{idNotification}")
public ResponseEntity deleteNotification(@PathVariable(name = "idNotification") Long idNotification) {
    if (idNotification == null) {
        return ResponseEntity.badRequest().body("Cannot remove Notification with null ID");
    }
    Notification  notification = notificationRepository.getOne(idNotification);
    if (notification == null) {
        return  ResponseEntity.notFound().build();
    }
    notificationRepository.delete(notification);
    return ResponseEntity.ok(notificationRepository.findAll());
}
}
