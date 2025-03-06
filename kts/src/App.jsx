import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import {Route, Routes} from "react-router-dom";
import RegisterPage from './features/auth/pages/RegisterPage';

function App() {

  return (
    <Routes>
      <Route path = "/login" element = {<LoginPage/>} />,
      <Route path = "/" element  = {<RegisterPage/>}/>
    </Routes>
  )
}

export default App
