import React from "react";
import ReactDOM from "react-dom/client";
import reportWebVitals from "./reportWebVitals";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Register from "./register/Register";
import Login from "./login/Login";
import Accounts from "./accounts/Accounts";

const root = ReactDOM.createRoot(document.getElementById("root"));

const router = createBrowserRouter([
  {
    path: "/arequipa",
    element: (
      <Register bankName="Arequipa" apiUrl="http://localhost:8085/api/users" />
    ),
  },
  {
    path: "/lima",
    element: (
      <Register bankName="Lima" apiUrl="http://localhost:8086/api/users" />
    ),
  },
  {
    path: "/cusco",
    element: (
      <Register bankName="Cusco" apiUrl="http://localhost:8087/api/users" />
    ),
  },
  {
    path: "/arequipa/login",
    element: (
      <Login bankName="Arequipa" apiUrl="http://localhost:8085/api/users" />
    ),
  },
  {
    path: "/lima/login",
    element: <Login bankName="Lima" apiUrl="http://localhost:8086/api/users" />,
  },
  {
    path: "/cusco/login",
    element: (
      <Login bankName="Cusco" apiUrl="http://localhost:8087/api/users" />
    ),
  },
  {
    path: "/:userId/accounts",
    element: <Accounts />,
  },
]);

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
