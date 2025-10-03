import './App.css'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Dashboard from "./components/Dashboard.jsx";
import LoginForm from "./components/LoginForm.jsx";
import RegisterForm from "./components/RegisterForm.jsx";

function App() {

  return (
      <Routes>
        <Route path="/" element={<LoginForm/>} />
          <Route path="/dashboard" element={<Dashboard/>} />
          <Route path="/login" element={<LoginForm/>} />
          <Route path="/register" element={<RegisterForm/>} />
          <Route path="/dashboard" element={<Dashboard/>} />
      </Routes>

  )
}

export default App
