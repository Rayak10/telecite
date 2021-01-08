package technocite.tn.telecite.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.expr.NewArray;
import technocite.tn.telecite.dto.Response;
import technocite.tn.telecite.entities.Bureau;
import technocite.tn.telecite.entities.Departement;
import technocite.tn.telecite.entities.Employe;
import technocite.tn.telecite.entities.Equipe;
import technocite.tn.telecite.entities.Reunion;
import technocite.tn.telecite.entities.Tache;
import technocite.tn.telecite.exception.ResourceNotFoundException;
import technocite.tn.telecite.jwt.JwtProvider;
import technocite.tn.telecite.repositories.IBureau;
import technocite.tn.telecite.repositories.IDepartement;
import technocite.tn.telecite.repositories.IEmploye;
import technocite.tn.telecite.repositories.IEquipe;
import technocite.tn.telecite.repositories.IReunion;
import technocite.tn.telecite.repositories.ITache;
import technocite.tn.telecite.services.EmployeService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/telecite/employes")
public class EmloyeController {
	   @Autowired
	    private JwtProvider jwtProvider;
	    @Autowired
	    private EmployeService employerepository;
	 
    @Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
	private IEmploye employeRepository;
	@Autowired
	private IEquipe equipeRepository;

	@Autowired
	private IDepartement departementRepository;

	@Autowired
	private IReunion reunionRepository;
	
	@GetMapping("/")
	public List<Employe> findAll() {
		List<Employe> employes= new ArrayList<>();
		employeRepository.findAll().forEach(employes::add);
		return employes; 
	}
	
	@GetMapping("/AllActives/{isActive}")
	public ResponseEntity findEmployesActivesOrNot(@PathVariable(name = "isActive") boolean isActive) {
		if (StringUtils.isEmpty(isActive) ) {
            return ResponseEntity.badRequest().body("Cannot find with empty employe active");
        }
		List<Employe> employes = employeRepository.findByActive(isActive);
		return  ResponseEntity.ok(employes);
	}

	@GetMapping("/{idEmploye}")
	
	public ResponseEntity findEmployeById(@PathVariable(name="idEmploye") Long idEmploye) { 
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		
		  if (idEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Employe with null ID");
	        }
	        Optional<Employe> employe = employeRepository.findById(idEmploye);
	        if (employe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(employe);
	}
@GetMapping("/photo/{idEmploye}")
	
	public ResponseEntity findPhotoEmployeById(@PathVariable(name="idEmploye") Long idEmploye) { 
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		
		  if (idEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve Employe with null ID");
	        }
	        Employe employe = employeRepository.getOne(idEmploye);
	        if (employe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        if(employe.getPhoto()==null) {
	        	return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok()
	        		.contentType(MediaType.IMAGE_GIF)
	        		.contentType(MediaType.IMAGE_JPEG)
	        		.contentType(MediaType.IMAGE_PNG)
	        		.body(new InputStreamResource(new ByteArrayInputStream(employe.getPhoto())));
	}
	@GetMapping("/nom/{nomEmploye}")
	public ResponseEntity findByNomEmploye(@PathVariable(name="nomEmploye") String nomEmploye) { 
		 if (nomEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve employe with null name");
	        }
		  Employe employe = employeRepository.findByNomEmploye(nomEmploye);
	        if (employe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(employe);
	}
	@GetMapping("/email/{email}")
	public ResponseEntity findByEmail(@PathVariable(name="email") String email) { 
		 if (email == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve employe with null name");
	        }
		  Employe employe = employeRepository.findByEmail(email);
	        if (employe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(employe);
	}
	
	
	@GetMapping("/nomPrenom/{nomEmploye}/{prenomEmploye}")

	public ResponseEntity findByNomEmployeAndPrenomEmploye(@PathVariable(name="nomEmploye") String nomEmploye,@PathVariable(name="prenomEmploye") String prenomEmploye) { 
		 if (nomEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot retrieve employe with null name");
	        }
		  Employe employe = employeRepository.findByNomEmployeAndPrenomEmploye(nomEmploye, prenomEmploye);
	        if (employe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok().body(employe);
	}
	
	@PostMapping("/saveEmployeProfile")
	public ResponseEntity<Response> saveEmployeProfile(@RequestParam("file") MultipartFile file, @RequestParam("employee") String employee) throws IOException{
		Employe employe =new ObjectMapper().readValue(employee, Employe.class);
		
		
		System.out.println("eeeeeeeeeeeeeeeeee"+employee);
		employe.setPhoto(file.getBytes());
		employe.setFileName(file.getOriginalFilename());
		employe.setPassword(passwordEncoder.encode(employe.getPassword()));

		Employe emp =employeRepository.save(employe);
		
		if(emp!=null) { 
		return new ResponseEntity<Response>(new Response ("employe is save successfully"),HttpStatus.OK);
	}else {
		return new ResponseEntity<Response>(new Response ("employe not saved"),HttpStatus.BAD_REQUEST);
	}
	}
	@PostMapping("/")
    	public ResponseEntity createEmploye(@RequestBody Employe employe) {
        if (employe == null) {
            return ResponseEntity.badRequest().body("Cannot create employe with empty fields");
        }
        if(employeRepository.existsByEmail(employe.getEmail())) {
			return new ResponseEntity<Response>(new Response("email is already taken"),HttpStatus.BAD_REQUEST);
		}
		employe.setPassword(passwordEncoder.encode(employe.getPassword()));

        Employe createEmploye = employeRepository.save(employe);
        return ResponseEntity.ok(employeRepository.findAll());
    }
	@PutMapping("/update/{idEmploye}")
	public ResponseEntity updateEmploye(@PathVariable(value = "idEmploye") Long idEmploye, @RequestBody 
		Employe employeeDetails) throws ResourceNotFoundException {
		Employe employe = employeRepository.findById(idEmploye)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + idEmploye));
		
		employe.setMatricule(employeeDetails.getMatricule());
		employe.setNomEmploye(employeeDetails.getNomEmploye());
		employe.setPrenomEmploye(employeeDetails.getPrenomEmploye());
		employe.setDateNaissance(employeeDetails.getDateNaissance());
		employe.setEmail(employeeDetails.getEmail());
		employe.setPassword(passwordEncoder.encode(employe.getPassword()));
		employe.setDateEmbauche(employeeDetails.getDateEmbauche());
		employe.setSalaire(employeeDetails.getSalaire());
		
		employe.setPost(employeeDetails.getPost());
		employe.setRole(employeeDetails.getRole());
		employe.setActive(employeeDetails.getActive());
		employe.setPhoto(employeeDetails.getPhoto());
		employe.setBureau(employeeDetails.getBureau());
		employe.setDepartement(employeeDetails.getDepartement());
		employe.setEquipe(employeeDetails.getEquipe());
		final Employe updatedEmploye = employeRepository.save(employe);
		 return ResponseEntity.ok(employeRepository.findAll());
	}
	@PostMapping("/login")
    public ResponseEntity login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return ResponseEntity.badRequest().body("Cannot login with empty employe email or password");
        }
        Employe authenticatedEmploye = employeRepository.findByEmailAndPassword(email, password);
        if (authenticatedEmploye == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authenticatedEmploye);
    }
//	 @PostMapping("/auth")
//	    public AuthResponse auth(@RequestBody AuthRequest request) {
//	        Employe e = employerepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
//	        String token = jwtProvider.generateToken(e.getEmail());
//	        return new AuthResponse(token);
//	    }
	 @PostMapping("/auth")
	    public ResponseEntity auth(@RequestBody AuthRequest request) {
		 Employe authenticatedEmploye= employerepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
	        String token = jwtProvider.generateToken(authenticatedEmploye.getEmail());
	       // return new AuthResponse(token);
	        return ResponseEntity.ok(authenticatedEmploye);
	    }
	
	 @DeleteMapping("/{idEmploye}")
	    public ResponseEntity deleteEmploye(@PathVariable(name = "idEmploye") Long idEmploye) {
	        if (idEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot remove employe with null ID");
	        }
	        Employe employe = employeRepository.getOne(idEmploye);
	        if (employe == null) {
	            return  ResponseEntity.notFound().build();
	        }
	        employeRepository.delete(employe);
	        return ResponseEntity.ok(employeRepository.findAll());
	    }
	 @GetMapping("/active/{idEmploye}/{isActive}")
	    public ResponseEntity employeActif(@PathVariable(name = "idEmploye") Long idEmploye, @PathVariable(name = "isActive") boolean isActive) {
	        if (idEmploye == null) {
	            return ResponseEntity.badRequest().body("Cannot see employe with null ID");
	        }
	        Employe employe = employeRepository.getOne(idEmploye);
	        if (employe == null) {
	            return  ResponseEntity.notFound().build();
	        }
	        employe.setActive(isActive);
	        return ResponseEntity.ok(employeRepository.save(employe));
	    }
	 @GetMapping("/employesEquipe/{idEquipe}")
	    public ResponseEntity findAllEemployesEquipe(@PathVariable Long idEquipe) {
	        if (idEquipe == null) {
	            return ResponseEntity.badRequest().body("Cannot find employes with null id");
	        }
	        Optional<Equipe> equipe = equipeRepository.findById(idEquipe);
	        if (equipe == null) {
	            return ResponseEntity.notFound().build();
	        }
	        List<Employe> equipeEmployes = employeRepository.findByEquipe(equipe);
	        
	        
	       

	        return ResponseEntity.ok(equipeEmployes);
	    }
	
	 
	    
	 
	 @GetMapping("/employesdepartement/{idDepartement}")
	    public ResponseEntity findAllEemployesRDepartement(@PathVariable Long idDepartement) {
	        if (idDepartement == null) {
	            return ResponseEntity.badRequest().body("Cannot find employes with null departement");
	        }
	        Optional<Departement> departement = departementRepository.findById(idDepartement);
	        System.out.println("55555555555555"+idDepartement);

	        if (departement == null) {
	            return ResponseEntity.notFound().build();
	        }
	        List<Employe> departementEmployes = employeRepository.findByDepartement(departement);
	        
	       

	        return ResponseEntity.ok(departementEmployes);
	    }


	 @GetMapping("/employesReunions/{idReunion}")
	    public ResponseEntity findAllEemployesReunion(@PathVariable Long idReunion) {
	        if (idReunion == null) {
	            return ResponseEntity.badRequest().body("Cannot find employes with null idReunion");
	        }
	        Optional<Reunion> reunion = reunionRepository.findById(idReunion);
	        System.out.println("55555555555555"+idReunion);

	        if (reunion == null) {
	            return ResponseEntity.notFound().build();
	        }
	        List<Employe> reunionEmployes = employeRepository.findByReunions(reunion);
	        
	       

	        return ResponseEntity.ok(reunionEmployes);
	    }

	
	
}
