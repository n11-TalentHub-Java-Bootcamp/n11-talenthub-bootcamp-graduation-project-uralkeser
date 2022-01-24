import "./FormItem.css";

const FormItem = ({field}) =>{
  return(
    <div className="form-item">
      <div className="col-25">
        <label>{field}:</label>
      </div>
      <div className="col-75">
        <input type="text" id={field}/>
      </div>
    </div>
  )
}

export default FormItem;