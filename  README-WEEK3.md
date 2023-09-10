# Cars 'R' Us

---

### Where and why you have used a @OneToMany annotation
dat3.car.entity.Member.java
- List<Reservation> reservations = new ArrayList<>();


### Where and why you have used a @ManyToOne annotation
dat3.car.entity.Reservation.java
- private Member member;
- private Car car;

### The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many

![ERD](https://github.com/davijoe/cars/assets/61663729/48e5eab6-4f59-43d5-a869-736a052f5aaf)

### How/where you have (if done) added user defined queries to you repositories
### a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
### a few words about where you have used inheritance in your project, and how it's reflected in your database
### What are the pros & cons of using the Single Table Strategy for inheritance?
### how are passwords stored in the database with the changes suggested in part-6 of the exercise
