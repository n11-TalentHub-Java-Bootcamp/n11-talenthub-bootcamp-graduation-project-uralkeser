import Config from "../../config/AppConfig";
import Header from "../Header/Header"
import "./Navbar.css"

const Navbar = (props) =>{
  return(
    <div id='navbar'>
        <Header/>
        <div id='navbar-buttons'>
          <button onClick={props.onHomeButtonClick}>{Config.homePage}</button>
          <button onClick={props.onFirstButtonClick}>{Config.firstPage}</button>
          <button onClick={props.onSecondButtonClick}>{Config.secondPage}</button>
          <button onClick={props.onThirdButtonClick}>{Config.thirdPage}</button>
        </div>
      </div>
  )
}

export default Navbar;