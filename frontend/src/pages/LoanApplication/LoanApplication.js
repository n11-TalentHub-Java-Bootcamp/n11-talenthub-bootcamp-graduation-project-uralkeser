import React from "react";
import serialize from "form-serialize";
import LoanApplicationForm from "../../component/LoanApplicationForm/LoanApplicationForm";
import AppConfig from "../../config/AppConfig";


class LoanApplication extends React.Component {

  handleSubmit = (e) =>{
    e.preventDefault();
    
    console.log(e);
  }

  render(){
    return (
      <div>

        <form id="customer-form">

          <div className="">
            <h2>Kredi Başvurusu</h2>
          </div>

          <LoanApplicationForm formItems={AppConfig.loanApplicationFields} onSubmit={this.handleSubmit}/>
        
        </form>
      </div>
    );
  }
}

export default LoanApplication;