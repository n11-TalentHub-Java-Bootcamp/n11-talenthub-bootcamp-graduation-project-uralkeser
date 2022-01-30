import React from "react";
import Config from "./config/AppConfig";
import {Route, Routes} from "react-router-dom";
import Navbar from "./component/Navbar/Navbar";
import Footer from "./component/Footer/Footer";
import HomePage from "./pages/HomePage/HomePage";
import LoanApplicationPage from "./pages/LoanApplicationPage/LoanApplicationPage";
import LoanApplicationResultsPage from "./pages/LoanApplicationResults/LoanApplicationResultsPage";
import UpdateClientPage from "./pages/UpdateClientPage/UpdateClientPage";

class App extends React.Component {
  render(){
    return (
      <div className="container">
        <Navbar/>
        <Routes>
          <Route path="/" element={<HomePage></HomePage>}/>
          <Route path="/loan-application" element={<LoanApplicationPage></LoanApplicationPage>}/>
          <Route path="/loan-application-results" element={<LoanApplicationResultsPage></LoanApplicationResultsPage>}/>
          <Route path="/updated-client-application" element={<UpdateClientPage></UpdateClientPage>}/>
        </Routes>
        <Footer/>
      </div>
    );
  }
}

export default App;
