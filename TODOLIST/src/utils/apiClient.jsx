import axios from "axios";


const BASE_URL = "http://localhost:8089";

const apiClient = axios.create({
    baseURL: BASE_URL,
    timeout: 5000,
    // CRITICAL: Tells the browser to include cookies in the request (required for HttpOnly JWTs)
    withCredentials: true,
    headers: {
        'Content-Type': 'application/json',
    },
});

// exception handler for api call
apiClient.interceptors.response.use(
    (response) => response,
    (error) => {
        // Example: If a 401 (Unauthorized) is received, you could redirect the user to the login page.
        if (error.response && error.response.status === 401) {
            console.error("401 Unauthorized: Session may have expired.");
            // window.location.href = '/login'; // Uncomment this line in a real app
        }

        return Promise.reject(error);
    }
);

export default apiClient;