# ToDoList Full Stack Application

A full-featured ToDo List app with a Java Spring Boot backend and a modern ReactJS frontend (Vite + TailwindCSS).

## Features
- User authentication and management
- Create, update, delete, and organize tasks
- Tagging and color-coding
- Calendar, Today, and Upcoming views
- Responsive UI with sidebar navigation
- Error handling and loading states

## Technologies Used
### Backend
- Java 21
- Spring Boot (Web, Data JPA, Security)
- Microsoft SQL Server

### Frontend
- React 19
- Vite
- TailwindCSS
- Axios
- React Router

## Folder Structure
```
ToDoList/
├── src/
│   ├── main/java/com/example/todolist/   # Spring Boot backend
│   └── main/resources/                  # Application config
├── TODOLIST/
│   ├── src/                            # React frontend
│   ├── public/                         # Static assets
│   ├── package.json                    # Frontend dependencies
│   └── vite.config.js                  # Vite config
├── pom.xml                             # Backend dependencies
└── README.md                           # Project documentation
```

## Getting Started
### Backend (Spring Boot)
1. Install Java 21 and Maven.
2. Configure your database in `src/main/resources/application.properties`.
3. Run:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend (ReactJS)
1. Navigate to the `TODOLIST` folder:
   ```bash
   cd TODOLIST
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```

## Usage
- Access the frontend at [http://localhost:8080](http://localhost:8080)
- The backend API runs on its configured port (default: 8080 or 8081)
- Register/login, create tasks, organize with tags, and view by date

## Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License
This project is for educational/demo purposes.

