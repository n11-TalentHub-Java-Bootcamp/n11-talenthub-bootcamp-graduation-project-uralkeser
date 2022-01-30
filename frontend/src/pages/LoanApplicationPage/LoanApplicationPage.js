import React from "react";
import ApplicationForm from "../../component/ApplicationForm/ApplicationForm";
import AppConfig from "../../config/AppConfig";
import InfoArea from "../../component/InfoArea/InfoArea";

class LoanApplicationPage extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      isSubmit: false,
      response: 'success'
    };
  }

  handleSubmit = (e) => {
    e.preventDefault();
    console.log(this.state);
    this.submitForm();
  }

  submitForm = () => {

    const uri = "http://localhost:8080/api/v1/clients";
    const requestBody = this.state;
    delete requestBody.isSubmit;
    delete requestBody.response;


    const requestOptions = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestBody)
    }

    fetch(uri, requestOptions)
      .then(response => {
        if (response.status != "201") {
          this.setState({
            response: 'unsuccessful',
          });
          return;
        }
        return response.json();
      }).then(res => {
        if (res) {
          this.setState({
            response: 'success',
          });
        }
      })
      .catch(error => {
        console.log(error);
        this.setState({
          response: 'unsuccessful',
        });
      });
    this.setState({
      isSubmit: true
    });
  }

  handleInputChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.id;

    this.setState({
      [name]: value
    });

  }

  render() {
    return (
      <>
        { this.state.isSubmit ?  <InfoArea response={this.state.response}></InfoArea> : <ApplicationForm title="Kişisel Bilgilerim" button="Bilgilerimi Kaydet ve Başvuru Yap" formItems={AppConfig.loanApplicationFields} onSubmit={this.handleSubmit} onChange={this.handleInputChange} />}
      </>
    );
  }
}

export default LoanApplicationPage;