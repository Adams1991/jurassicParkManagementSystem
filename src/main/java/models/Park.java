package models;

import db.DBHelper;
import db.DBPark;

import javax.persistence.*;
import javax.smartcardio.ATR;

import java.util.ArrayList;

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
    private Attraction attraction;

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
        if (paddock.isPaddockBroken()) ;
        carnivore.eat(10);
    }

    public boolean hasPaddocks(){
        if (this.paddocks.size() != 0){
            return true;
        }
        return false;
    }


    public List<Paddock> returnPaddocksWithCarns() {
        List<Paddock> paddocks = DBPark.paddockInPark(this);
        List<Paddock> paddocksWithCarn = new ArrayList<>();
        for (Paddock paddock : paddocks) {
            if (paddock.CarnAmount() != 0)
                paddocksWithCarn.add(paddock);
        }
        return paddocksWithCarn;
    }

    public List<Carnivore> returnListOfCarnsinPaddockList(List<Paddock> paddocksWithCarns) {
        List<Carnivore> carnivores = new ArrayList<>();
        for (Paddock paddock : paddocksWithCarns) {
            carnivores.addAll(paddock.getCarnivores());
        }
        return carnivores;
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
        int randomPersonEaten = randForStaffOrVisitor.nextInt(2) + 1;
        for (Paddock paddock : paddocksWithCarns) {
            if (paddock.isPaddockBroken()) {
                DBHelper.update(paddock);
                if (visitor != null && carnivore != null) {
                    int visitorMeat = carnivore.kill(visitor);
                    carnivore.eat(visitorMeat);
                    DBHelper.update(carnivore);
                }

                if (randomPersonEaten == 1) {
                    if (visitor != null) {
                        visitor.setHasBeenEaten(true);
                        DBHelper.update(visitor);
                    }
                } else {
                    if (staff != null) {
                        staff.setHasBeenEaten(true);
                        DBHelper.update(staff);
                    }
                }
            }
        }
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attraction_id", nullable = true)
    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public List<Visitor> showVisitorsWhoAreOver18() {
        List<Visitor> visitors = DBPark.visitorsInPark(this);
        Attraction attraction = DBPark.getAttractionsinPark(this);
        List<Visitor> suitableVisitors = new ArrayList<>();
        for (Visitor visitor : visitors) {
                if (attraction.isAllowed(visitor)) {
                suitableVisitors.add(visitor);
            }
        }
        return suitableVisitors;
    }

    public void visitorGoesToAttraction(Visitor visitor){
        setTill(till += attraction.getCost());
        visitor.buyTicketForAttraction(attraction);
        DBHelper.update(visitor);
        DBHelper.update(this);
    }
}