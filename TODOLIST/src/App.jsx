import './App.css'
import { Routes, Route} from 'react-router-dom';
import Dashboard from "./components/dashboard/Dashboard.jsx";
import LoginForm from "./components/authen/LoginForm.jsx";
import RegisterForm from "./components/authen/RegisterForm.jsx";
import AuthProvider from "./components/authen/AuthProvider.jsx";
import ErrorProvider from "./components/errorhandler/ErrorProvider.jsx";
import GlobalContext from "./components/context/GlobalContext.jsx";
import NotFound from "./utils/NotFound.jsx";


function App() {

  return (
      <GlobalContext>
          <ErrorProvider>
              <AuthProvider>
                  <Routes>
                      <Route path="*" element={<NotFound />} />
                      <Route path="/" element={<LoginForm/>} />
                      <Route path="/login" element={<LoginForm/>} />
                      <Route path="/register" element={<RegisterForm/>} />
                      <Route path="/dashboard" element={
                          //<ProtectedRoute>
                          <Dashboard />
                          //</ProtectedRoute>
                      }/>
                  </Routes>
              </AuthProvider>
          </ErrorProvider>
      </GlobalContext>


  )
}

export default App
