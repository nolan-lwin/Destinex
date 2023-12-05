# Team 08 CSCI205 Project: Destinex - The P2P Global Delivery Platform

## Team Members and Roles

- **Professor:** Joshua Stough
- **Product Owner:** Nolan Lwin
- **Scrum Master:** Chang Min Bark
- **Developers:** Hung Pham, Hung Ngo

### High-fidelity Prototype

Our entire front-end website is meticulously crafted on [Figma](https://www.figma.com/), starting with a high-fidelity interactive and wire-framed prototype. You can explore the detailed design and functionality by clicking on this [link](https://www.figma.com/proto/l6GGqXkRlP2Vc38Z6dYV2I/Destinex?page-id=0%3A1&type=design&node-id=1-2&viewport=1660%2C127%2C0.27&t=4RNBm18FlhMa4Fih-1&scaling=scale-down&mode=design).

### Front-end Description

Our front-end, crafted with React Native, provides a dynamic and intuitive user interface for our website platform. It's designed to offer a seamless and engaging user experience, allowing users to easily navigate and interact with our service. The front-end is continually updated to meet evolving user preferences and to incorporate the latest UI/UX trends.

### Key Features

#### Responsive Design
- **Adaptive Layouts**: The UI adapts gracefully to different screen sizes and orientations, ensuring a consistent experience across various devices.
- **Intuitive Interactions**: User interactions are designed to be intuitive, with clear navigation and interactive elements that make the process of selecting and sending gifts effortless.

#### Real-Time Updates
- **WebSocket Integration**: Leveraging WebSocket for real-time updates, our front-end ensures that users receive immediate feedback and notifications, enhancing the interactivity of the application.

#### User-Centric Functionality
- **Easy Navigation**: The app layout is structured for easy navigation, making it straightforward for users to find what they need.
- **Interactive Elements**: From selecting gifts to checking out, every element is designed with the user in mind, ensuring a smooth and enjoyable experience.

### Package Structure

The front-end is organized into several directories, each serving a specific function:

The front-end of our application is organized into specific directories, each tailored for a specific purpose to enhance maintainability and scalability:

- `assets`: Houses static files used throughout the app.
  - `img`: Contains image files.
  - `svg`: Stores SVG files for high-quality vector graphics.

- `components`: Reusable components that form the building blocks of the app's interface.
  - `footer`: Components related to the footer section of the app.
  - `header`: Various header components, allowing for different header styles and functionalities.
    - `header-logo-only`: A minimal header with just the logo.
    - `header-no-background`: A transparent header, adaptable to any background.
    - `header-purple`: A header with a distinctive purple style.

- `context`: Manages the application state using React's Context API for state management across different components.

- `networks`: Handles network operations and data fetching.
  - `api`: Contains functions and utilities for making API calls.
  - `hooks`: Custom React hooks for network operations.
  - `utils`: Utility functions supporting network operations.

- `pages`: Represents the different pages or screens of the application.
  - `common`: Common components or layouts shared across different pages.
    - `error`: Error page or error handling components.
  - `granters`: Components and pages specific to the granter's side of the application.
    - `bank`, `congrats`, `details`, `granter-home`, `jobs`, `setup`, `signup`, `waiting-payment`, `wish-granted`: Various subdirectories for different aspects and functionalities related to granters.
  - `wishers`: Components and pages specific to the wisher's side of the application.
    - `home`, `signin`, `signup`, `wish-additional`, `wish-confirmation`, `wish-product`, `wish-recipient`, `wish-success`, `wish-summary`, `wish-tracking`: Different subdirectories for various functionalities and features for wishers.

### Third-Party Libraries

We use a range of third-party libraries to enhance the functionality and user experience of our React Native app:

1. **React Navigation**: For managing navigation between different screens in the app.
2. **Axios**: For making HTTP requests to the backend services.

### Running the Front-end

To get the front-end up and running:

1. **Navigate to the Front-end Directory**:
   - Open a terminal and switch to the front-end directory:
     ```
     cd path/to/csci205_final_project/frontend
     ```
     Replace `path/to` with the actual path to your project.

2. **Install Dependencies**:
   - Install all required dependencies via npm or yarn:
     ```
     npm i --force
     ```

3. **Start the Application**:
   - Launch the app on a simulator or a connected device:
     ```
     npm start
     ```

4. **Verify the App is Running**:
   - The application will automatically open in your browser or you can type `http://localhost:3000` manually in your website browser.