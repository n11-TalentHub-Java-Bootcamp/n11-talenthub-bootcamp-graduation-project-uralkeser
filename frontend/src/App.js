import React from "react";
import Config from "./config/AppConfig";
import Navbar from "./component/Navbar/Navbar";
import PageNavigator from "./component/PageNavigator/PageNavigator";
import Footer from "./component/Footer/Footer";

class App extends React.Component {
  
  state = {
    pageType: Config.homePage
  }

  render(){
    return (
      <div className="container">
        <Navbar
          onHomeButtonClick = { () => this.setState({pageType : Config.homePage})}
          onFirstButtonClick = { () => this.setState({pageType : Config.firstPage})}
          onSecondButtonClick = { () => this.setState({pageType : Config.secondPage})}
          onThirdButtonClick = { () => this.setState({pageType : Config.thirdPage})}

        />
        <PageNavigator pageType={this.state.pageType}/>
        <Footer/>
      </div>
    );
  }
}

export default App;
