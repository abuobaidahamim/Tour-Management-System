import java.io.*;
import java.util.*;
 
class TourismManagementSystem {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>(); // Stores username and password

    static class Destination {
        String name;
        String weather;
        List<String> tips;
        Map<String, Double> packages;
        Map<String, String> packageDetails;

        Destination(String name, String weather, List<String> tips, Map<String, Double> packages, Map<String, String> packageDetails) {
            this.name = name;
            this.weather = weather;
            this.tips = tips;
            this.packages = packages;
            this.packageDetails = packageDetails;
        }
    }

    static class Booking {
        String travelType;
        boolean returnTicket;
        double travelCost;
        String hotel;
        double hotelCost;
        double totalCost;

        Booking(String travelType, boolean returnTicket, double travelCost, String hotel, double hotelCost) {
            this.travelType = travelType;
            this.returnTicket = returnTicket;
            this.travelCost = travelCost;
            this.hotel = hotel;
            this.hotelCost = hotelCost;
            this.totalCost = travelCost + hotelCost;
        }

        void displayBooking() {
            System.out.println("Travel Type: " + travelType);
            System.out.println("Return Ticket: " + (returnTicket ? "Yes" : "No"));
            System.out.println("Travel Cost: $" + travelCost);
            System.out.println("Hotel: " + hotel);
            System.out.println("Hotel Cost: $" + hotelCost);
            System.out.println("Total Cost: $" + totalCost);
        }
    }

    static List<Destination> destinations = new ArrayList<>();

    static void initializeData() {
        loadUserData(); // Load user data from file
        destinations.add(new Destination(
                "Paris",
                "Mild and Pleasant",
                Arrays.asList("Carry a light jacket", "Learn basic French phrases", "Carry an umbrella"),
                Map.of("Romantic Getaway", 1500.0, "Family Trip", 2000.0, "Solo Adventure", 1200.0),
                Map.of(
                        "Romantic Getaway", "Eiffel Tower, Seine River, Montmartre",
                        "Family Trip", "Louvre Museum, Disneyland Paris, Versailles",
                        "Solo Adventure", "Latin Quarter, Catacombs, Musée d'Orsay"
                )
        ));
        destinations.add(new Destination(
                "Tokyo",
                "Cool and Dry",
                Arrays.asList("Carry comfortable walking shoes", "Try the local sushi", "Use public transport"),
                Map.of("Cultural Tour", 1800.0, "Foodie Package", 1600.0, "Anime Lovers Tour", 1700.0),
                Map.of(
                        "Cultural Tour", "Meiji Shrine, Asakusa, Imperial Palace",
                        "Foodie Package", "Tsukiji Market, Ramen Street, Omoide Yokocho",
                        "Anime Lovers Tour", "Akihabara, Ghibli Museum, Odaiba"
                )
        ));
        destinations.add(new Destination(
                "New York",
                "Chilly and Vibrant",
                Arrays.asList("Carry a heavy coat in winter", "Explore on foot", "Visit Times Square at night"),
                Map.of("City Explorer", 2200.0, "Art and Culture Tour", 2500.0, "Shopping Spree", 2400.0),
                Map.of(
                        "City Explorer", "Statue of Liberty, Central Park, Empire State Building",
                        "Art and Culture Tour", "Metropolitan Museum of Art, Broadway, MoMA",
                        "Shopping Spree", "Fifth Avenue, Soho, Macy's Herald Square"
                )
        ));
        destinations.add(new Destination(
                "Rome",
                "Warm and Sunny",
                Arrays.asList("Stay hydrated", "Visit early mornings to avoid crowds", "Learn basic Italian phrases"),
                Map.of("Historical Journey", 1400.0, "Romantic Escape", 1600.0, "Foodie Tour", 1300.0),
                Map.of(
                        "Historical Journey", "Colosseum, Roman Forum, Pantheon",
                        "Romantic Escape", "Trevi Fountain, Piazza Navona, Trastevere",
                        "Foodie Tour", "Campo de' Fiori, Testaccio Market, Jewish Ghetto"
                )
        ));
        destinations.add(new Destination(
                "Sydney",
                "Warm and Breezy",
                Arrays.asList("Wear sunscreen", "Explore the beaches", "Try Australian seafood"),
                Map.of("Adventure Trip", 1700.0, "Relaxation Package", 1600.0, "Family Holiday", 1800.0),
                Map.of(
                        "Adventure Trip", "Sydney Harbour Bridge Climb, Blue Mountains, Bondi Beach",
                        "Relaxation Package", "Sydney Opera House, Manly Beach, Darling Harbour",
                        "Family Holiday", "Taronga Zoo, Sea Life Sydney Aquarium, Luna Park"
                )
        ));
        destinations.add(new Destination(
                "Cairo",
                "Hot and Arid",
                Arrays.asList("Carry a hat and sunglasses", "Stay hydrated", "Respect local customs"),
                Map.of("Historical Wonders", 1300.0, "Cultural Immersion", 1200.0, "Desert Safari", 1400.0),
                Map.of(
                        "Historical Wonders", "Pyramids of Giza, Sphinx, Egyptian Museum",
                        "Cultural Immersion", "Old Cairo, Khan el-Khalili, Al-Azhar Mosque",
                        "Desert Safari", "Giza Desert, White Desert, Siwa Oasis"
                )
        ));
        destinations.add(new Destination(
                "Rio de Janeiro",
                "Warm and Humid",
                Arrays.asList("Pack light clothing", "Stay cautious in crowds", "Try Brazilian street food"),
                Map.of("Carnival Experience", 1800.0, "Beach Getaway", 1500.0, "Adventure Tour", 1600.0),
                Map.of(
                        "Carnival Experience", "Sambadrome, Lapa Arches, Maracanã Stadium",
                        "Beach Getaway", "Copacabana, Ipanema, Barra da Tijuca",
                        "Adventure Tour", "Christ the Redeemer, Sugarloaf Mountain, Tijuca Forest"
                )
        ));
        destinations.add(new Destination(
                "Cape Town",
                "Cool and Breezy",
                Arrays.asList("Carry a jacket for windy days", "Try local wines", "Explore the coastlines"),
                Map.of("Nature and Wildlife", 1400.0, "Coastal Adventure", 1500.0, "Cultural Journey", 1300.0),
                Map.of(
                        "Nature and Wildlife", "Table Mountain, Cape of Good Hope, Kirstenbosch Gardens",
                        "Coastal Adventure", "Boulders Beach, Robben Island, Camps Bay",
                        "Cultural Journey", "District Six Museum, Bo-Kaap, Greenmarket Square"
                )
        ));
        destinations.add(new Destination(
                "Bangkok",
                "Hot and Humid",
                Arrays.asList("Carry light clothing", "Try local street food", "Visit temples respectfully"),
                Map.of("City Explorer", 1200.0, "Food Tour", 1100.0, "Cultural Adventure", 1300.0),
                Map.of(
                        "City Explorer", "Grand Palace, Chatuchak Market, Lumphini Park",
                        "Food Tour", "Chinatown, Khaosan Road, Floating Markets",
                        "Cultural Adventure", "Wat Arun, Wat Pho, Jim Thompson House"
                )
        ));
        destinations.add(new Destination(
                "London",
                "Cold and Rainy",
                Arrays.asList("Carry an umbrella", "Wear comfortable walking shoes", "Explore museums"),
                Map.of("Historical Tour", 2000.0, "Shopping Spree", 2200.0, "Royal Experience", 2100.0),
                Map.of(
                        "Historical Tour", "Tower of London, British Museum, Westminster Abbey",
                        "Shopping Spree", "Oxford Street, Harrods, Camden Market",
                        "Royal Experience", "Buckingham Palace, Windsor Castle, Kensington Gardens"
                )
        ));
        destinations.add(new Destination(
                "Moscow",
                "Cold and Snowy",
                Arrays.asList("Wear warm clothing", "Try Russian cuisine", "Learn a few Russian phrases"),
                Map.of("Winter Wonderland", 1600.0, "Historical Tour", 1500.0, "City Explorer", 1400.0),
                Map.of(
                        "Winter Wonderland", "Red Square, Gorky Park, Izmailovsky Market",
                        "Historical Tour", "Kremlin, St. Basil's Cathedral, Bolshoi Theatre",
                        "City Explorer", "Arbat Street, Moscow Metro, Sparrow Hills"
                )
        ));
        destinations.add(new Destination(
                "Singapore",
                "Warm and Humid",
                Arrays.asList("Stay hydrated", "Use public transport", "Try hawker food"),
                Map.of("City Highlights", 1400.0, "Nature Tour", 1500.0, "Family Fun", 1600.0),
                Map.of(
                        "City Highlights", "Marina Bay Sands, Merlion Park, Chinatown",
                        "Nature Tour", "Gardens by the Bay, Sentosa Island, Singapore Zoo",
                        "Family Fun", "Universal Studios, SEA Aquarium, Night Safari"
                )
        ));
    }

    static void displayDestinations() {
        System.out.println("Available Destinations:");
        for (int i = 0; i < destinations.size(); i++) {
            System.out.println((i + 1) + ". " + destinations.get(i).name);
        }
    }

    static void displayDestinationDetails(int index) {
        Destination destination = destinations.get(index);
        System.out.println("Destination: " + destination.name);
        System.out.println("Weather: " + destination.weather);
        System.out.println("Travel Tips: ");
        for (String tip : destination.tips) {
            System.out.println("- " + tip);
        }
        System.out.println("Available Packages:");
        for (Map.Entry<String, Double> entry : destination.packages.entrySet()) {
            System.out.println(entry.getKey() + " - $" + entry.getValue());
            System.out.println("Includes: " + destination.packageDetails.get(entry.getKey()));
        }
    }

    static Booking createBooking(Destination destination) {
        System.out.println("Enter your location (city name):");
        String userLocation = scanner.next();
        double distance = calculateDistance(userLocation, destination.name);
        double travelCost = calculateTravelCost(distance);

        String travelType;

        while (true) {
            System.out.println("Select Travel Type (Plane/Ship):");
            travelType = scanner.next();

            if (travelType.equalsIgnoreCase("Plane") || travelType.equalsIgnoreCase("Ship") ) {
                break;
            } else {
                System.out.println("Error: Please type exactly 'Plane' or 'Ship'.");
            }
        }

        String returnTicketInput;

        while (true) {
            System.out.println("Do you need a return ticket? (yes/no):");
            returnTicketInput = scanner.next();

            if (returnTicketInput.equalsIgnoreCase("yes") || returnTicketInput.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("Error: Please type exactly 'yes' or 'no'.");
            }
        }
        boolean returnTicket = returnTicketInput.equalsIgnoreCase("yes");

        if (returnTicket) travelCost *= 2;

        int hotelChoice;

        while (true) {
            System.out.println("Choose Hotel:");
            System.out.println("1. Luxury ($300 per night)");
            System.out.println("2. Standard ($150 per night)");
            System.out.println("3. Budget ($50 per night)");

            if (scanner.hasNextInt()) {
                hotelChoice = scanner.nextInt();

                if (hotelChoice == 1 || hotelChoice == 2 || hotelChoice == 3) {
                    break;
                } else {
                    System.out.println("Error: Please select 1, 2, or 3.");
                }
            } else {
                System.out.println("Error: Please enter a valid number.");
                scanner.next();
            }
        }

        double hotelCost = hotelChoice == 1 ? 300 : hotelChoice == 2 ? 150 : 50;

        System.out.println("How many nights will you stay?");
        int nights = scanner.nextInt();
        hotelCost *= nights;

        return new Booking(travelType, returnTicket, travelCost, hotelChoice == 1 ? "Luxury" : hotelChoice == 2 ? "Standard" : "Budget", hotelCost);
    }

    static double calculateDistance(String userLocation, String destinationName) {
        double distance = 0;

        // Existing distances for Dhaka
        if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Paris")) {
            distance = 8000; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Tokyo")) {
            distance = 7000;
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("New York")) {
            distance = 12000; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Rome")) {
            distance = 7500; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Sydney")) {
            distance = 9200; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Cairo")) {
            distance = 6000; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Rio de Janeiro")) {
            distance = 15000; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Cape Town")) {
            distance = 11000; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Bangkok")) {
            distance = 1500; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("London")) {
            distance = 8000; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Moscow")) {
            distance = 5000; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Dhaka") && destinationName.equalsIgnoreCase("Singapore")) {
            distance = 2900; // Approximate distance in km
        }

        // Distances for New York
        else if (userLocation.equalsIgnoreCase("New York") && destinationName.equalsIgnoreCase("Paris")) {
            distance = 5800; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("New York") && destinationName.equalsIgnoreCase("Tokyo")) {
            distance = 10800;
        } else if (userLocation.equalsIgnoreCase("New York") && destinationName.equalsIgnoreCase("Sydney")) {
            distance = 16000;
        } else if (userLocation.equalsIgnoreCase("New York") && destinationName.equalsIgnoreCase("Cairo")) {
            distance = 9000;
        } else if (userLocation.equalsIgnoreCase("New York") && destinationName.equalsIgnoreCase("Cape Town")) {
            distance = 12500;
        }

        // Distances for London
        else if (userLocation.equalsIgnoreCase("London") && destinationName.equalsIgnoreCase("Paris")) {
            distance = 350; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("London") && destinationName.equalsIgnoreCase("Tokyo")) {
            distance = 9600;
        } else if (userLocation.equalsIgnoreCase("London") && destinationName.equalsIgnoreCase("Sydney")) {
            distance = 17000;
        } else if (userLocation.equalsIgnoreCase("London") && destinationName.equalsIgnoreCase("Cairo")) {
            distance = 3600;
        } else if (userLocation.equalsIgnoreCase("London") && destinationName.equalsIgnoreCase("Rio de Janeiro")) {
            distance = 9300;
        }

        // Distances for Tokyo
        else if (userLocation.equalsIgnoreCase("Tokyo") && destinationName.equalsIgnoreCase("Paris")) {
            distance = 9700; // Approximate distance in km
        } else if (userLocation.equalsIgnoreCase("Tokyo") && destinationName.equalsIgnoreCase("New York")) {
            distance = 10800;
        } else if (userLocation.equalsIgnoreCase("Tokyo") && destinationName.equalsIgnoreCase("Sydney")) {
            distance = 7800;
        } else if (userLocation.equalsIgnoreCase("Tokyo") && destinationName.equalsIgnoreCase("Cairo")) {
            distance = 9600;
        } else if (userLocation.equalsIgnoreCase("Tokyo") && destinationName.equalsIgnoreCase("Bangkok")) {
            distance = 4600;
        }

        return distance;
    }

    static double calculateTravelCost(double distance) {
        if (distance < 1000) return 100; // Cheap travel
        else if (distance < 5000) return 200; // Moderate travel
        else return 300; // Expensive travel
    }

    static void makePayment(double amount) {
        int paymentChoice;

        while (true) {
            System.out.println();
            System.out.println("Select Payment Method:");
            System.out.println("1. Mobile Banking");
            System.out.println("2. Online Banking");

            if (scanner.hasNextInt()) { // Check if the input is an integer
                paymentChoice = scanner.nextInt();

                // Validate if the input is 1 or 2
                if (paymentChoice == 1 || paymentChoice == 2) {
                    break;
                } else {
                    System.out.println("Error: Please select either 1 or 2.");
                }
            } else {
                System.out.println("Error: Please enter a number.");
                scanner.next();
            }
        }

        if (paymentChoice == 1) {
            System.out.println("Enter Mobile Banking number:");
            String mobileNumber = scanner.next();
            System.out.println("Enter OTP sent to " + mobileNumber + ":");
            String otp = scanner.next();
            System.out.println("Payment of $" + amount + " completed via Mobile Banking. Mobile Number: " + mobileNumber);
        } else if (paymentChoice == 2) {
            System.out.println("Enter Card Number (Visa/MasterCard):");
            String cardNumber = scanner.next();
            System.out.println("Enter Card Password:");
            String cardPassword = scanner.next();
            System.out.println("Payment of $" + amount + " completed via Online Banking. Card Number: " + cardNumber);
        } else {
            System.out.println("Invalid payment method. Please try again.");
        }
    }

    static void registerUser() {
        System.out.println("Register a new account:");

        String username;
        // Loop until a valid username is provided
        while (true) {
            System.out.println("Enter username:");
            username = scanner.next();

            if (users.containsKey(username)) {
                System.out.println("Username already exists. Please try again.");
            } else {
                break;
            }
        }

        String password;
        // Loop until a valid password is provided
        while (true) {
            System.out.println("Enter a password (at least 6 characters):");
            password = scanner.next();

            if (password.length() >= 6) {
                break; // Exit the loop if the password is valid
            } else {
                System.out.println("Password must be at least 6 characters. Please try again.");
            }
        }

        // Save the new user
        users.put(username, password);
        saveUserData();
        System.out.println("Registration successful!");
    }

    static boolean loginUser() {
        String username;
        String password;

        // Loop until the user provides correct credentials
        while (true) {
            System.out.println("Login to your account:");

            // Input username
            System.out.println("Enter username:");
            username = scanner.next();

            // Input password
            System.out.println("Enter password:");
            password = scanner.next();

            // Check if username and password are correct
            if (users.containsKey(username) && users.get(username).equals(password)) {
                System.out.println("Login successful! Welcome, " + username + "!");
                return true;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
    }


    static void saveUserData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }
    }

    static void loadUserData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            users = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new HashMap<>(); // Initialize empty map if file not found
        }
    }

    public static void main(String[] args) {
        initializeData();
        System.out.println();
        System.out.println("==========================");
        System.out.println("Welcome to the Tour Guider");
        System.out.println("==========================");
        System.out.println();

        while (true) {
            System.out.println();
            System.out.println("Select an option from below");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println();

            int choice = scanner.nextInt();
            if (choice == 1) {
                registerUser();
            } else if (choice == 2) {
                if (loginUser()) {
                    while (true) {
                        System.out.println("\n\nSelect an option from below");
                        System.out.println("1. View Destinations");
                        System.out.println("2. Book a Trip");
                        System.out.println("3. Logout");

                        int userChoice = scanner.nextInt();
                        if (userChoice == 1) {
                            displayDestinations();

                            int destinationChoice;

                            // Loop until the user makes a valid choice
                            while (true) {
                                System.out.println("Enter the number of the destination to view details:");

                                if (scanner.hasNextInt()) {  // Check if input is an integer
                                    destinationChoice = scanner.nextInt();

                                    // Validate if the choice is within the range
                                    if (destinationChoice > 0 && destinationChoice <= destinations.size()) {
                                        displayDestinationDetails(destinationChoice - 1);
                                        break;
                                    } else {
                                        System.out.println("Invalid choice. Please select a number between 1 and " + destinations.size() + ".");
                                    }
                                } else {
                                    System.out.println("Error: Please enter a valid number.");
                                    scanner.next();
                                }
                            }
                        } else if (userChoice == 2) {
                            displayDestinations();
                            int destinationChoice;
                            while (true) {

                                System.out.println("Enter the number of the destination to book a trip:");

                                if (scanner.hasNextInt()) { // Check if input is an integer
                                    destinationChoice = scanner.nextInt();

                                    // Validate if the choice is within the range of destinations
                                    if (destinationChoice > 0 && destinationChoice <= destinations.size()) {
                                        Destination selectedDestination = destinations.get(destinationChoice - 1);
                                        Booking booking = createBooking(selectedDestination);
                                        if (booking != null) {
                                            System.out.println("\nBooking Details:");
                                            booking.displayBooking();
                                            makePayment(booking.totalCost);
                                        }
                                        break;
                                    } else {
                                        System.out.println("Invalid choice. Please select a valid destination.");
                                    }
                                } else {
                                    System.out.println("Error: Please enter a valid number.");
                                    scanner.next();
                                }
                            }
                        } else if (userChoice == 3) {
                            System.out.println("Logged out successfully.");
                            break;
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                }
            } else if (choice == 3) {
                System.out.println("Thank you for using the Tour Guider!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
