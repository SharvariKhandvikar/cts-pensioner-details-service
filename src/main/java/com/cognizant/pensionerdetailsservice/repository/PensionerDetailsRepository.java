package com.cognizant.pensionerdetailsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.pensionerdetailsservice.model.PensionerDetails;

//@Transactional
//@Repository
public interface PensionerDetailsRepository extends JpaRepository<PensionerDetails, String> {
	
//	Logger logger = LoggerFactory.getLogger(PensionerDetailsServiceApplication.class);
//	
//	@Autowired
//	EntityManager em;
//	
//	public PensionerDetails findByAdharNumber(String adharNumber) {
//		
//		return em.find(PensionerDetails.class, adharNumber);
//	}
//	
//	public PensionerDetails savePensioner(PensionerDetails pd) {
//		
//		em.flush();
//	}
//	

}
