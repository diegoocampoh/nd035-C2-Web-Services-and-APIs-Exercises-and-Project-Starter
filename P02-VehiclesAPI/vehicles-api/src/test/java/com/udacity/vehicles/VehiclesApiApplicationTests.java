package com.udacity.vehicles;

import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class VehiclesApiApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postCar() {

        Car car = createSampleCar();
        ResponseEntity<String> response =
                this.restTemplate
                        .postForEntity("http://localhost:" + port + "/cars", car, String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        System.out.println(response);
    }

    @Test
    public void contextLoads() {
    }

    private Car createSampleCar(){
        Car car = new Car();
        car.setCondition(Condition.NEW);

        Details carDetails = new Details();
        carDetails.setBody("hatch");
        carDetails.setModel("Corolla 2008");
        carDetails.setManufacturer(new Manufacturer(100, "Audi"));
        car.setDetails(carDetails);
        return car;
    }

}
