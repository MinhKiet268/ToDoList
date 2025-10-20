import axios from "axios";
import {useAuth} from "../components/authen/AuthProvider.jsx";
import {useError} from "../components/errorhandler/ErrorProvider.jsx";

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

let onLogout;
let onRefresh;

export const setupApiInterceptor = (refreshFunc,logoutFunc) => {
    onLogout = logoutFunc;
    onRefresh = refreshFunc;
    apiClient.interceptors.response.use(
        //on success
        (response) => response,

        //on denied
        async (error) => {
            const originalRequest = error.config;
            // Example: If a 401 (Unauthorized) is received, you could redirect the user to the login page.
            if (error.response.status === 401 && !originalRequest._retry) {
                originalRequest._retry = true;

                try{
                    refreshFunc();
                    return apiClient(originalRequest)
                }catch(error) {
                    console.error(error);
                    logoutFunc();
                }
                // window.location.href = '/login'; // Uncomment this line in a real app
            }
            return Promise.reject(error);
        }
    );
}
// exception handler for api call


export default apiClient;