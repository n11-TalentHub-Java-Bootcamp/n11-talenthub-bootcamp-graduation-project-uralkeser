import Config from "../../config/AppConfig";
import HomePage from "../../pages/Home/Home";
import ClientInfoPage from "../../pages/ClientInfo/ClientInfo";
import LoanApplication from "../../pages/LoanApplication/LoanApplication";
import LoanApplicationStatus from "../../pages/LoanApplicationStatus/LoanApplicationStatus";

const PageNavigator = ({pageType}) => {
  if(pageType === Config.firstPage){
    return (<LoanApplication/>);
  }
  else if(pageType === Config.secondPage){
    return (<LoanApplicationStatus/>);
  }
  else if(pageType === Config.thirdPage){
    return (<ClientInfoPage/>);
  }
  else if(pageType === Config.homePage){
    return (<HomePage/>);
  }
  else{
    return('wrong page type parameter');
  }
}

export default PageNavigator;