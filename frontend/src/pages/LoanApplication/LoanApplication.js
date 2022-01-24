import React from "react";
import LoanApplicationForm from "../../component/LoanApplicationForm/LoanApplicationForm";
import AppConfig from "../../config/AppConfig";


class LoanApplication extends React.Component {

  constructor(props){
    super(props);
    this.state = {
    
    };
  }

  handleSubmit = (e) =>{
    e.preventDefault();
    console.log(this.state);
  }

  handleInputChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.name;

    this.setState({
      [name]: value
    });

  }

  render(){
    return (
      <div>
        <div className="">
          <h2>Kredi Başvurusu</h2>
        </div>
        <LoanApplicationForm formItems={AppConfig.loanApplicationFields} onSubmit={this.handleSubmit} onChange={this.handleInputChange}/>
      </div>
    );
  }
}

export default LoanApplication;