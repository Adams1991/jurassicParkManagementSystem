package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paddocks")
public class Paddock {
    private int id;
    private Park park;
    private String name;
    private List<Carnivore> carnivores;
    private List<Herbivore> herbivores;
    private boolean isPaddockBroken;

    public Paddock(Park park, String name, boolean isPaddockBroken) {
        this.park = park;
        this.name = name;
        this.isPaddockBroken = false;
    }

    public Paddock() {
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

    @ManyToOne
    @JoinColumn(name = "park_id", nullable = false)
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "paddock", fetch = FetchType.LAZY)
    public List<Carnivore> getCarnivores() {
        return carnivores;
    }

    public void setCarnivores(List<Carnivore> carnivores) {
        this.carnivores = carnivores;
    }

    @OneToMany(mappedBy = "paddock", fetch = FetchType.LAZY)
    public List<Herbivore> getHerbivores() {
        return herbivores;
    }

    public void setHerbivores(List<Herbivore> herbivores) {
        this.herbivores = herbivores;
    }

    @Column(name = "is_paddock_broken")
    public boolean isPaddockBroken() {
        return this.isPaddockBroken;
    }

    public void setPaddockBroken(boolean paddockBroken) {
        isPaddockBroken = paddockBroken;
    }

    public void addCarnivoreToCarnivorePaddock(Carnivore carnivore) {
        carnivores.add(carnivore);
    }

    public void breakout(Carnivore carnivore) {
        if (carnivore.hungerLevel < 50 && this.isPaddockBroken == false) {
            setPaddockBroken(true);
        }
    }
}



