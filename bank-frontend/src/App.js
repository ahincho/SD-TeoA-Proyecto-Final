import React from 'react';
import Register from './register/Register'
import Login from './login/Login'
import Table from './accounts/Table';

function App() {
  return (
    <React.StrictMode>
      { /* <Login/> */}
      { /* <Register/> */}
      <Table bankName = "Arequipa Bank" apiUrl = "http://localhost:8085/api/accounts/u"/>
      <Table bankName = "Cusco Bank" apiUrl = "http://localhost:8086/api/accounts/u"/>
      <Table bankName = "Lima Bank" apiUrl = "http://localhost:8087/api/accounts/u"/>
    </React.StrictMode>
  );
}

export default App;
