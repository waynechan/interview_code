class TrieNode:
    def __init__(self):
        self.is_end = False
        self.children = {}


class Trie:

    def __init__(self):
        self.root = TrieNode()

    # @param {string} word
    # @return {void}
    def insert(self, word):
        cur = self.root
        for c in word:
            if c not in cur.children:
                cur.children[c] = TrieNode()
            cur = cur.children[c]
        cur.is_end = True

    # @param {string} word
    # @return {boolean}
    def search(self, word):
        cur = self.root
        for c in word:
            if c in cur.children:
                cur = cur.children[c]
            else:
                return False

        return cur.is_end

    # @param {string} prefix
    # @return {boolean}
    def starts_with(self, prefix):
        cur = self.root
        for c in prefix:
            if c in cur.children:
                cur = cur.children[c]
            else:
                return False

        return True


if __name__ == "__main__":
    trie = Trie()
    trie.insert('test')
    print(trie.search('test'))
    print(trie.starts_with('te'))
    print(trie.search('hello'))
    print(trie.starts_with('he'))
