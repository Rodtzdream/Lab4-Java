import java.util.*;

public class BoxOfficeGuideForMovies
{
    private final Map<String, MovieData> movieMap;

    public BoxOfficeGuideForMovies() {
        this.movieMap = new LinkedHashMap<>();
    }

    private record MovieData(String title, String director, String genre, int yearReleased, double boxOfficeEarnings) {
    }

    public void addMovie(String title, String director, String genre, int yearReleased, double boxOfficeEarnings) {
        if (movieMap.containsKey(title)) {
            throw new IllegalArgumentException("Movie with title '" + title + "' already exists");
        }
        movieMap.put(title, new MovieData(title, director, genre, yearReleased, boxOfficeEarnings));
    }

    public void removeMovie(String title) {
        if (!movieMap.containsKey(title)) {
            throw new IllegalArgumentException("Movie with title '" + title + "' not found");
        }
        movieMap.remove(title);
    }

    public MovieData findMovieByTitle(String title)
    {
        return movieMap.get(title);
    }

    public List<MovieData> getAllMoviesSortedByBoxOfficeEarnings()
    {
        List<MovieData> sortedMovies = new ArrayList<>(movieMap.values());
        sortedMovies.sort(Comparator.comparingDouble(MovieData::boxOfficeEarnings).reversed());
        return sortedMovies;
    }

    public void printMovieInfoByTitle(String title) {
        MovieData movie = movieMap.get(title);
        if (movie != null)
        {
            System.out.println("Title: " + movie.title);
            System.out.println("Director: " + movie.director);
            System.out.println("Genre: " + movie.genre);
            System.out.println("Year Released: " + movie.yearReleased);
            System.out.println("Box Office Earnings: " + movie.boxOfficeEarnings);
        }
        else
        {
            System.out.println("Movie with title '" + title + "' not found");
        }
    }

    public void printAllMovies()
    {
        for (MovieData movieData : movieMap.values())
        {
            System.out.println("Title: " + movieData.title + ", Director: " + movieData.director +
                    ", Genre: " + movieData.genre + ", Year Released: " + movieData.yearReleased +
                    ", Box Office Earnings: " + movieData.boxOfficeEarnings);
        }
    }

    public static void main(String[] args)
    {
        BoxOfficeGuideForMovies boxOfficeGuide = new BoxOfficeGuideForMovies();
        boxOfficeGuide.addMovie("Movie 1", "Director 1", "Genre 1", 2020, 1000000);
        boxOfficeGuide.addMovie("Movie 2", "Director 2", "Genre 2", 2021, 2500000);
        boxOfficeGuide.addMovie("Movie 3", "Director 3", "Genre 3", 2022, 1500000);
        boxOfficeGuide.addMovie("Movie 4", "Director 4", "Genre 4", 2023, 2000000);
        boxOfficeGuide.addMovie("Movie 5", "Director 5", "Genre 5", 2024, 3000000);

        String searchTitle = "Movie 2";
        MovieData foundMovie = boxOfficeGuide.findMovieByTitle(searchTitle);
        if (foundMovie != null)
        {
            System.out.println("Found movie: " + foundMovie.title());
        }
        else
        {
            System.out.println("Movie with title '" + searchTitle + "' not found");
        }

        String movieToRemove = "Movie 3";
        boxOfficeGuide.removeMovie(movieToRemove);
        System.out.println("Removed movie: " + movieToRemove);

        List<MovieData> sortedMovies = boxOfficeGuide.getAllMoviesSortedByBoxOfficeEarnings();
        System.out.println("All movies sorted by box office earnings:");
        for (MovieData movie : sortedMovies)
        {
            System.out.println(movie.title() + ": " + movie.boxOfficeEarnings());
        }

        System.out.println("\nPress Enter to exit...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}