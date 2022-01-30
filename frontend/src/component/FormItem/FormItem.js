import "./FormItem.css";

const FormItem = (props) =>{
  return(
    <div className="form-item">
      <div className="col-25">
        <label>{props.field}:</label>
      </div>
      <div className="col-75">
        <input type="text" id={props.id} name={props.field} onChange={props.handleInputChange}/>
      </div>
    </div>
  )
}

export default FormItem;