class Trie() {
    /** Initialize your data structure here. */
    val ch=Array<Trie?>(26) { null }
    var isFinish=false

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        if(word.length==0) {
            isFinish=true
            return
        }
        if(ch[word[0]-'a'] == null) {
            ch[word[0]-'a'] = Trie()
        }
        ch[word[0]-'a']?.insert(word.substring(1))
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        if(word.length==0){
            return isFinish
        }
        
        return ch[word[0]-'a']?.search(word.substring(1)) ?: false
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        if(prefix.length==0) {
            for(i in 0..ch.size-1) {
                if(ch[i]!=null) {
                    return true
                }
            }
            return isFinish
        }
        
        return ch[prefix[0]-'a']?.startsWith(prefix.substring(1)) ?: false
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */