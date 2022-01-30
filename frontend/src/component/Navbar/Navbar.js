import Config from "../../config/AppConfig";
import Header from "../Header/Header"
import "./Navbar.css"

const Navbar = (props) =>{
  return(
    <div id='navbar'>
      <Header/>
      <div id='navbar-buttons'>
        <a href="/" >{Config.homePage}</a>
        <a href="/loan-application" >{Config.firstPage}</a>
        <a href="/loan-application-results" >{Config.secondPage}</a>
        <a href="/updated-client-application" >{Config.thirdPage}</a>
      </div>
    </div>
  )
}

export default Navbar;