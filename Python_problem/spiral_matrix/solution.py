class Solution:

    def get_matrix(self, n):
        matrix = [[0 for _ in range(n)] for _ in range(n)]

        left, right, top, bottom = 0, n - 1, 0, n - 1
        number = 1

        while left <= right and top <= bottom:
            for j in range(left, right + 1):
                matrix[top][j] = number
                number += 1
            for i in range(top + 1, bottom):
                matrix[i][right] = number
                number += 1
            for j in reversed(range(left, right + 1)):
                if top < bottom:
                    matrix[bottom][j] = number
                    number += 1
            for i in reversed(range(top + 1, bottom)):
                if left < right:
                    matrix[i][left] = number
                    number += 1
            left, right, top, bottom = left + 1, right - 1, top + 1, bottom - 1

        return matrix


if __name__ == "__main__":
    result = Solution().get_matrix(3)

    print(result)
