
const InfoArea = (props) =>{
  if(props.data)
  {
    return(
      <div>
      
        {
          props.data.map((result) =>{
            return(
              <div key = {result.applicationDate}>
                <p>sonuç: {result.status} miktar: {result.loanAmount} tarih: {result.applicationDate}</p>
              </div>
            );
          })
        }

      </div>
    );
  }
  else{
    return(<p>Info{props.response}</p>);  
  }

}

export default InfoArea;