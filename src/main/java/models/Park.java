package models;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "parks")
public class Park {
    private int id;
    private int till;
    private String name;
    private List<Staff> staff;
    private List<Visitor> visitors;
    private List<Paddock> paddocks;

    public Park() {
    }

    public Park(String name, int till) {
        this.name = name;
        this.till = till;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "park", fetch = FetchType.LAZY)
    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    // changed to eager to fix issue
    @OneToMany(mappedBy = "park", fetch = FetchType.LAZY)
    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    @OneToMany(mappedBy = "park", fetch = FetchType.LAZY)
    public List<Paddock> getPaddocks() {
        return paddocks;
    }

    public void setPaddocks(List<Paddock> paddocks) {
        this.paddocks = paddocks;
    }

    @Column(name = "till")
    public int getTill() {
        return this.till;
    }

    public void setTill(int till) {
        this.till = till;
    }

    public void visitorGetEaten(Paddock paddock, Carnivore carnivore){
        if (paddock.isPaddockBroken() == true);
        carnivore.eat(10);
    }
}
