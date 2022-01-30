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
    this.submitForm();
  }

  submitForm = () => {

    const uri = "http://localhost:8080/api/v1/clients";

    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(this.state)
    }

    fetch(uri,requestOptions)
      .then(response => {
      if (response.ok) {
        //change page
      }
      return response.json()})
      .then( res =>  {
        console.log(res);
        console.log(res.status);
      })
      .catch(error => console.log(error));
  }


  handleInputChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.id;

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