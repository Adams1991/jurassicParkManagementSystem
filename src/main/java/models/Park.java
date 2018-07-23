package models;

import db.DBHelper;
import db.DBPark;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "parks")
public class Park {
    private int id;
    private int till;
    private String name;
    private List<Staff> staff;
    private List<Visitor> visitors;
    private List<Paddock> paddocks;
    private DinoFeeding dinoFeeding;

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

    public void visitorGetEaten(Paddock paddock, Carnivore carnivore) {
        if (paddock.isPaddockBroken() == true) ;
        carnivore.eat(10);
    }


    public List<Paddock> returnPaddocksWithCarns(){
        List<Paddock> paddocks = DBPark.paddockInPark(this);
        List<Paddock> paddocksWithCarn = new ArrayList<>();
        for (Paddock paddock : paddocks) {
            if (paddock.CarnAmount() != 0)
                paddocksWithCarn.add(paddock);
        }
        return paddocksWithCarn;
    }

    public void starveDinoInAListofPaddocks(List<Paddock> paddocksWithCarns) {
        for (Paddock paddock : paddocksWithCarns) {
            for (Carnivore carnivore : paddock.getCarnivores()) {
                carnivore.starveCarnivore();
                DBHelper.update(carnivore);
                paddock.breakout(carnivore);
                DBHelper.update(paddock);
            }
        }
    }

    public void eatVisitorIfPaddocksBroken(List<Paddock> paddocksWithCarns, Visitor visitor, Carnivore carnivore, Staff staff) {
        Random randForStaffOrVisitor = new Random();
        int randomPersonEaten = randForStaffOrVisitor.nextInt(2)+1;
                    for (Paddock paddock : paddocksWithCarns) {
                if (paddock.isPaddockBroken()){
                    DBHelper.update(paddock);
                    if (visitor != null){
                    int visitorMeat = carnivore.kill(visitor);
                    carnivore.eat(visitorMeat);
                    DBHelper.update(carnivore);}

                    if(randomPersonEaten == 1){
                        if (visitor != null){
                    visitor.setHasBeenEaten(true);
                    DBHelper.update(visitor);}}else{
                        if (staff != null){
                    staff.setHasBeenEaten(true);
                    DBHelper.update(staff);}}
                }
            }
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dino_feeding_id", nullable = false)
    public DinoFeeding getDinoFeeding() {
        return dinoFeeding;
    }

    public void setDinoFeeding(DinoFeeding dinoFeeding) {
        this.dinoFeeding = dinoFeeding;
    }
}

