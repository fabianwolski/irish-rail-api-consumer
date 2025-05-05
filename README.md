# Irish Rail API Consumer

A simple Java application that consumes the Irish Rail API to display real-time train timetables.


![image](https://github.com/user-attachments/assets/ef9e5e0d-e8ff-461d-8d20-bbbeae6b920c)


## Overview

This application fetches real-time train information from the Irish Rail API for any specified station, parses the XML data, and generates a clean HTML timetable. The timetable displays key information including:

- Origin station
- Destination station
- Expected arrival time
- Expected departure time
- Arrival time at destination
- Current status

## Features

- Simple command-line interface
- Real-time data from the official Irish Rail API
- Clean, minimalist HTML output
- Supports all stations on the Irish Rail network
- Automatic page refresh to keep information current
- Simple countdown timer showing when data will refresh

## Requirements

- Java 11 or higher
- Internet connection to access the Irish Rail API

## Usage

1. Compile and run the `Main.java` file
2. When prompted, enter a station code (e.g., "CNLLY" for Connolly Station) or press Enter to use the default (Enfield)
3. Enter a time window in minutes (up to 90) or press Enter to use the default
4. The application will fetch and display the train timetable in your default web browser

## Common Station Codes

- CNLLY - Connolly Station (Dublin)
- HSTON - Heuston Station (Dublin)
- ENFLD - Enfield Station
- PERSE - Pearse Station (Dublin)
- GALWY - Galway (Ceannt) Station
- LMRCK - Limerick (Colbert) Station

## Project Structure

- `Main.java` - Entry point, handles user input and coordinates the process
- `IrishRailAPI.java` - Handles API communication with the Irish Rail service
- `TrainDetails.java` - Data model for train information
- `ParseXML.java` - Parses XML responses from the API
- `GenerateHTML.java` - Creates the HTML timetable
- `SaveFile.java` - Utility for saving files

The `resources` directory contains templates for the HTML output:
- `template.html` - Main HTML structure
- `styles.css` - Minimal styling
- `script.js` - Simple countdown for page refresh

## API Documentation

This project uses the public Irish Rail Realtime API. More information about the API can be found at [http://api.irishrail.ie/realtime/](http://api.irishrail.ie/realtime/)

## Future Enhancements

The application includes a `getStations()` method in the `IrishRailAPI` class that fetches all available stations from the Irish Rail API. Future enhancements could include:

- Station selection UI that displays all available stations
- Search functionality to find stations by name
- Favorites system to save commonly used stations
- Map visualization of station locations

## License

[MIT License](LICENSE)

## Acknowledgments

- Irish Rail for providing the public API
