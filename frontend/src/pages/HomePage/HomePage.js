import React from "react";
import AppConfig from "../../config/AppConfig";
class HomePage extends React.Component {

  render(){
    return (
      <div>
        <h2>Anasayfa</h2>
        <h3>{AppConfig.projectName}</h3>
        <h4>{AppConfig.projectInfo}</h4>
        {
          AppConfig.projectFunctionalitiesInfo.map(func =>{
           return(<h5 key={func}>{func}</h5>)
          })
        }
      </div>
    );
  }
}

export default HomePage;
