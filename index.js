import React from 'react-native';

import { AppRegistry } from 'react-native';
import App from './App';

function AppRegister(props) {
  return (
    <App {...props} />
  );
}

AppRegistry.registerComponent('FromNative', () => AppRegister);
