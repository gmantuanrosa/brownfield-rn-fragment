import React from 'react-native';

import { AppRegistry } from 'react-native';
import App from './App';

function AppRegister(props) {
  <App props={props} />;
}

AppRegistry.registerComponent('FromNative', () => AppRegister);
