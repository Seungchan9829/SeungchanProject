import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import LoginPage from './features/auth/pages/LoginPage.jsx';
import RegisterPage from './features/auth/pages/RegisterPage.jsx';
import HomePage from './features/home/HomePage.jsx';
import MainLayout from './shared/components/MainLayout.jsx';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import AccountManagementPage from './features/account/AccountManagementPage.jsx';
const router = createBrowserRouter([
  { path : "/",
    element : <MainLayout/>,
    children : [
      {path : 'home', element: <HomePage/>},
      {path : 'account-management', element : <AccountManagementPage/>}
    ]

  },
  {
    path: "/login",
    element: <LoginPage/>
  },
  {
    path: "/register",
    element: <RegisterPage/>
  }
]);

const queryClient = new QueryClient();

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <QueryClientProvider client={queryClient}>
      <RouterProvider router={router} />
    </QueryClientProvider>
  </StrictMode>,
)
