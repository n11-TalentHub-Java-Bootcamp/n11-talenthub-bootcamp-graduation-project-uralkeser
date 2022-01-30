import React from "react";
import AppConfig from "../../config/AppConfig";
import InfoArea from "../../component/InfoArea/InfoArea";
import ApplicationForm from "../../component/ApplicationForm/ApplicationForm";


class UpdateClientPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isSubmit: props.isSubmit,
      response: false
    };
  }

  handleSubmit = (e) => {
    e.preventDefault();
    console.log(this.state);
    this.submitForm();
  }

  submitForm = () => {

    const requestBody = this.state;
    const uri = "http://localhost:8080/api/v1/clients/"+requestBody.ssn+"/"+requestBody.birthdate;
    delete requestBody.isSubmit;
    delete requestBody.response;
    delete requestBody.ssn;
    delete requestBody.birthdate;

    const requestOptions = {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestBody)
    }

    fetch(uri, requestOptions)
      .then(response => {
        if (response.status != "200") {
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

  render(){
    return (
      <>
        { this.state.isSubmit ?  <InfoArea response={this.state.response}></InfoArea> : <ApplicationForm title="Güncel Bilgilerim" button="Bilgilerimi Güncelle ve Yeni Başvuru Yap" formItems={AppConfig.updateActionFields} onSubmit={this.handleSubmit} onChange={this.handleInputChange} />}
      </>
    );
  }
}

export default UpdateClientPage;