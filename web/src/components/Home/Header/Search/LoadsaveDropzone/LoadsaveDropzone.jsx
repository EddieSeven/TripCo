import React, {Component} from 'react';
import Dropzone from 'react-dropzone';


class LoadsaveDropzone extends React.Component {
    render() {
        let total = 0;
        let data = this.props.pairs;
        if (data.length !== 0){
            for(let i = 0; i < data.length; i++){
                let distance = data[i].props.dist;
                console.log(distance); // For testing, otherwise
                total += distance;     // Can be done on 1 line
            }
        }

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
