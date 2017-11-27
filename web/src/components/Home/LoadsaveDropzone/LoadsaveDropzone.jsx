import React, {Component} from 'react';
import Dropzone from 'react-dropzone';


class LoadsaveDropzone extends React.Component {
    render() {

        return  (
        <span>
            <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                <button type="button" >Upload JSON</button>
            </Dropzone>
        </span>
        );
    }

    drop(acceptedFiles) {
        console.log("Accepting drop");
        acceptedFiles.forEach(file => {
            console.log("Filename:", file.name, "File:", file);
            console.log(JSON.stringify(file));
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);
                    console.log(JsonObj);
                    this.props.browseFile(JsonObj);
                };
            })(file).bind(this);

            fr.readAsText(file);
        });
    }
}
export default LoadsaveDropzone;
