import heapq


class PriorityQueue:
    def __init__(self):
        self._queue = []

    def push(self, item):
        heapq.heappush(self._queue, item)

    def pop(self):
        return heapq.heappop(self._queue)

    def size(self):
        return len(self._queue)


class Solution:

    def merge_overlap(self, list, min_overlap_length):

        removed_list_indices = {}

        #initialize priority queue
        queue = PriorityQueue()
        for i in range(0, len(list)):
            for j in range(0, len(list)):
                if i != j:
                    length = self.suffix_prefix_match(list[i], list[j], min_overlap_length)
                    queue.push((-length, i, j))

        while queue.size() != 0:
            negative_length, i, j = queue.pop()

            if not i in removed_list_indices and not j in removed_list_indices:
                max_length = -negative_length
                new_string = list[i] + list[j][max_length:]
                list.append(new_string)
                last_index = len(list)-1

                removed_list_indices[i] = i
                removed_list_indices[j] = j

                for k in range(0, len(list)-1):
                    if not k in removed_list_indices:
                        length = self.suffix_prefix_match(list[k], list[last_index], min_overlap_length)
                        queue.push((-length, k, last_index))

                        length = self.suffix_prefix_match(list[last_index], list[k], min_overlap_length)
                        queue.push((-length, last_index, k))

        return new_string

    def suffix_prefix_match(self, a, b, min_overlap_length):
        """ Return length of longest suffix of 'a' matching
            a prefix of 'b' that is at least 'min_overlap_length'
            characters long.  If no such overlap exists,
            return 0. """
        index = 0  # start at the left
        while True:
            index = a.find(b[:min_overlap_length], index)  # look for b's suffx in a
            if index == -1:
                return 0
            # found occurrence; check for full suffix/prefix match
            if b.startswith(a[index:]):
                return len(a) - index
            index += 1  # move just past previous match


if __name__ == "__main__":
    solution = Solution()
    result = solution.merge_overlap(['all is well', 'ell that en', 'hat end', 't ends well'], 3)
    print(result)


