import React from 'react';
import { View, Text, StyleSheet, TextInput } from 'react-native';

type NativeProps = {
  view_id: string
}

function App({ view_id }: NativeProps): React.JSX.Element {
  return (
    <View style={styles.container}>
      <Text style={styles.text}>This is React Native on an Android Fragment.</Text>
      <Text >View id: ${view_id}</Text>
      <TextInput style={styles.input} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  text: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  input: {
    backgroundColor: '#DCDCDC',
    width: 400,
  }
});

export default App;
