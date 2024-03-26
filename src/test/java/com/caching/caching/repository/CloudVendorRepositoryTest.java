package com.caching.caching.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import com.caching.caching.entity.CloudVendor;

@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CloudVendorRepositoryTest {

	@Autowired
	private CloudVendorRepository cloudVendorRepository;
	
	 @Autowired
	 private TestEntityManager entityManager;
	 
	
	
	CloudVendor cloudVendor;
	
	@BeforeEach
	void setUp() {
		System.out.println("****************************************");
		 cloudVendor = new CloudVendor("4","Amazon",
	                "USA", "xxxxx");
	        cloudVendorRepository.save(cloudVendor);
	        System.out.println("****************************************");   
	}
	@AfterEach
    void tearDown() {
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }
	
	@Test
	void testFindByVendorName__Found() {
		System.out.println("###################################*");

		
		List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("Amazon");
		System.out.println(cloudVendorList);
//		assertThat(cloudVendorList.get(0).getVendorName()).isEqualTo("Amazon");
        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        assertThat(cloudVendorList.get(0).getVendorAddress())
                .isEqualTo(cloudVendor.getVendorAddress());
	}
}
