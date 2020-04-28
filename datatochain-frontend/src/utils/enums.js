function getRelationStatus() {
  return {
    'REALATION': {
      value: 0,
      label: '未解除'
    },
    'UNREALATION': {
      value: 1,
      label: '解除'
    }
  }
}

function getCodeMode() {
  return {
    'ASCII': {
      value: 1,
      label: 'ASCII'
    },
    'GB18030': {
      value: 2,
      label: 'GB18030'
    },
    'GB2312': {
      value: 3,
      label: 'GB2312'
    },
    'GBK': {
      value: 4,
      label: 'GBK'
    },
    'ISO': {
      value: 5,
      label: 'ISO-8859-1'
    },
    'UTF16': {
      value: 6,
      label: 'UTF-16'
    },
    'UTF8': {
      value: 7,
      label: 'UTF-8'
    }
  }
}

function getEncryptionMode() {
  return {
    'NO': {
      value: 0,
      label: 'NO'
    },
    'AES': {
      value: 1,
      label: 'AES'
    },
    'BASE64': {
      value: 2,
      label: 'BASE64'
    },
    'DES': {
      value: 3,
      label: 'DES'
    },
    'HMAC': {
      value: 4,
      label: 'HMAC'
    },
    'MD5': {
      value: 5,
      label: 'MD5'
    },
    'PBE': {
      value: 6,
      label: 'PBE'
    },
    'RSA': {
      value: 7,
      label: 'RSA'
    },
    'SHA': {
      value: 8,
      label: 'SHA'
    }
  }
}

function getChainType() {
  return {
    'NOTOCHAINBLOCK': {
      value: 0,
      label: 'NO-TO-CHAINBLOCK'
    },
    'NULSMAINNET': {
      value: 1,
      label: 'NULS-MAIN-NET'
    },
    'NULSTESTNET': {
      value: 2,
      label: 'NULS-TEST-NET'
    }
  }
}

function getFieldType() {
  return {
    'BLOB': {
      value: 1,
      label: 'BLOB'
    },
    'CHAR': {
      value: 2,
      label: 'CHAR'
    },
    'DATETIME': {
      value: 3,
      label: 'DATETIME'
    },
    'DECIMAL': {
      value: 4,
      label: 'DECIMAL'
    },
    'FLOAT': {
      value: 5,
      label: 'FLOAT'
    },
    'TEXT': {
      value: 6,
      label: 'TEXT'
    },
    'TIMESTAMP': {
      value: 7,
      label: 'TIMESTAMP'
    },
    'TINYINT': {
      value: 8,
      label: 'TINYINT'
    },
    'VARCHAR': {
      value: 9,
      label: 'VARCHAR'
    }
  }
}

function getAllowNull() {
  return {
    'ALLOW': {
      value: 0,
      label: '允许'
    },
    'UNALLOW': {
      value: 1,
      label: '不允许'
    }
  }
}

function getIsCreate() {
  return {
    'NO': {
      value: 0,
      label: '未创建'
    },
    'YES': {
      value: 1,
      label: '已创建'
    }
  }
}

function getIsKey() {
  return {
    'NO': {
      value: 0,
      label: '否'
    },
    'YES': {
      value: 1,
      label: '是'
    }
  }
}

export default {
  getRelationStatus,
  getCodeMode,
  getEncryptionMode,
  getChainType,
  getFieldType,
  getAllowNull,
  getIsCreate,
  getIsKey,
  findEnumLabel(value, enums) {
    for (const key in enums) {
      const item = enums[key]
      if (item.value === value) {
        return item.label
      }
    }
    return ''
  }
}
