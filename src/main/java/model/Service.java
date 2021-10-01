package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private int serviceId;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UsedService> listOfUsedService;

    @Column(name = "name", nullable = false, length = 100)
    @NotBlank
    private String name;

    @Column(name = "unity", nullable = false, length = 100)
    @NotBlank
    private String unity;

    @Column(name = "price", nullable = false)
    private float price;

    public Service() {
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Set<UsedService> getListOfUsedService() {
        return listOfUsedService;
    }

    public void setListOfUsedService(Set<UsedService> listOfUsedService) {
        this.listOfUsedService = listOfUsedService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
