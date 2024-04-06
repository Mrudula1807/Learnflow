import java.util.*;

class Movie {
    private String title;
    private String director;
    private int durationMinutes;

    public Movie(String title, String director, int durationMinutes) {
        this.title = title;
        this.director = director;
        this.durationMinutes = durationMinutes;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}

class Showtime {
    private Movie movie;
    private Date startTime;
    private int availableSeats;

    public Showtime(Movie movie, Date startTime, int availableSeats) {
        this.movie = movie;
        this.startTime = startTime;
        this.availableSeats = availableSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            System.out.println("Seat booked successfully.");
        } else {
            System.out.println("Sorry, no available seats for this showtime.");
        }
    }

    public void cancelSeat() {
        availableSeats++;
        System.out.println("Seat canceled successfully.");
    }
}

class BookingSystem {
    private List<Movie> movies = new ArrayList<>();
    private List<Showtime> showtimes = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Showtime> getShowtimes() {
        return showtimes;
    }

    public void displayShowtimes() {
        System.out.println("Available Showtimes:");
        for (Showtime showtime : showtimes) {
            System.out.println("Movie: " + showtime.getMovie().getTitle() +
                               ", Start Time: " + showtime.getStartTime() +
                               ", Available Seats: " + showtime.getAvailableSeats());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Movie movie1 = new Movie("Inception", "Christopher Nolan", 148);
        Movie movie2 = new Movie("The Dark Knight", "Christopher Nolan", 152);
        Showtime showtime1 = new Showtime(movie1, new Date(), 100);
        Showtime showtime2 = new Showtime(movie2, new Date(), 80);

        BookingSystem bookingSystem = new BookingSystem();
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);
        bookingSystem.addShowtime(showtime1);
        bookingSystem.addShowtime(showtime2);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Browse Movies");
            System.out.println("2. View Showtimes");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nMovies Available:");
                    for (Movie movie : bookingSystem.getMovies()) {
                        System.out.println(movie.getTitle());
                    }
                    break;
                case 2:
                    bookingSystem.displayShowtimes();
                    break;
                case 3:
                    bookingSystem.displayShowtimes();
                    System.out.print("Choose showtime index: ");
                    int showtimeIndex = scanner.nextInt();
                    Showtime selectedShowtime = bookingSystem.getShowtimes().get(showtimeIndex);
                    selectedShowtime.bookSeat();
                    break;
                case 4:
                    bookingSystem.displayShowtimes();
                    System.out.print("Choose showtime index: ");
                    int cancelShowtimeIndex = scanner.nextInt();
                    Showtime cancelShowtime = bookingSystem.getShowtimes().get(cancelShowtimeIndex);
                    cancelShowtime.cancelSeat();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
        scanner.close();
    }
}
