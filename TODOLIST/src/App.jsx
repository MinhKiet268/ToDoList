import './App.css'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Dashboard from "./components/Dashboard.jsx";
import LoginForm from "./components/LoginForm.jsx";
import RegisterForm from "./components/RegisterForm.jsx";
import AuthProvider from "./components/AuthProvider.jsx";
import ProtectedRoute from "./components/ProtectedRoute.jsx";

function App() {

  return (
      <AuthProvider>
          <Routes>
              <Route path="/" element={<LoginForm/>} />
              <Route path="/login" element={<LoginForm/>} />
              <Route path="/register" element={<RegisterForm/>} />
              <Route path="/dashboard" element={<ProtectedRoute> <Dashboard /> </ProtectedRoute>} />
          </Routes>
      </AuthProvider>
  )
}

export default App
